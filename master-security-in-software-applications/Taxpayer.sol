// SPDX-License-Identifier: MIT
pragma solidity ^0.8.22;

contract Taxpayer {

 uint age; 

 bool isMarried; 

 /* Reference to spouse if person is married, address(0) otherwise */
 address spouse; 


address  parent1; 
address  parent2; 

 /* Constant default income tax allowance */
 uint constant  DEFAULT_ALLOWANCE = 5000;

 /* Constant income tax allowance for Older Taxpayers over 65 */
  uint constant ALLOWANCE_OAP = 7000;

 /* Income tax allowance */
 uint tax_allowance; 

 uint income; 


 //constructor(address p1, address p2) {
 constructor() {
   age = 0;
   isMarried = false;
   //parent1 = p1;
   //parent2 = p2;
   spouse = address(0);
   income = 0;
   tax_allowance = DEFAULT_ALLOWANCE;
 } 


 //We require new_spouse != address(0);
 function marry(address new_spouse) public {
  // Added checks to see if the two contracts are not already married and if the spouse address is valid 
  require(!isMarried, "You shouldn't be already married");
  require(new_spouse != address(0), "Cannot marry with this address owner");
  require(new_spouse != address(this), "Cannot marry yourself");
  Taxpayer sp = Taxpayer(address(new_spouse));
  require(!sp.getIsMarried(), "Your spouse shouldn't be married"); 
  require(age >= 18, "You cannot be underage");
  spouse = new_spouse;
  isMarried = true;
  sp.setSpouse(address(this));
  // require(marriageSymmetry(), "Asymmetric marriage");    can be omittet to save gas
 }
 
 function divorce() public {
  require(isMarried, "You should be married, to divorce");	// I can only divorce if I'm married
  Taxpayer sp = Taxpayer(address(spouse));
  spouse = address(0);
  isMarried = false;
  sp.resetSpouse(address(this));
  // Additions for the third part
  if (age >= 65) {
  	tax_allowance = ALLOWANCE_OAP;
  } 
  else {
  	tax_allowance = DEFAULT_ALLOWANCE;
  }
 }

 /* Transfer part of tax allowance to own spouse */
 function transferAllowance(uint change) public payable{
  require(change <= tax_allowance, "Change cannot be over your tax allowance");
  require(change >= 0, "Change cannot be less than zero"); // being an uint is already >= 0, but I don't want unexpected behavior
  tax_allowance = tax_allowance - change;
  Taxpayer sp = Taxpayer(address(spouse));
  sp.setTaxAllowance(sp.getTaxAllowance()+change);
 }

 function haveBirthday() public {
  age++;
  // Addition for the third part
  if (age == 65) {
  	tax_allowance = tax_allowance + ALLOWANCE_OAP - DEFAULT_ALLOWANCE;
  }
 }

function setSpouse(address sp) public {
    require(!isMarried, "You shouldn't be already married");  
    require(Taxpayer(address(sp)).getSpouse() == address(this), "Asymmetric marriage");
    require(sp != address(0), "Invalid address");
    require(sp != address(this), "Cannot marry with yourself");
    require(age >= 18, "You cannot marry while still underage");
    spouse = sp;
    isMarried = true;
    //require(marriageSymmetry());  I don't need this anymore if I keep the initial require statements
}

function resetSpouse(address sp) public {
    // I need these because if the spouse is still married, then I'm not calling this function
    // from the divorce() one. But this cannot be done due to the marriage symmetry property 
    require(isMarried, "You have to be married"); 
    require(Taxpayer(address(sp)).getIsMarried() == false);
    require(spouse == sp);
    spouse = address(0);
    isMarried = false;
    if (age >= 65) {
  	tax_allowance = ALLOWANCE_OAP;
    } 
    else {
    	tax_allowance = DEFAULT_ALLOWANCE;
    }
}

function getSpouse() public view returns (address) {
    return spouse;
}

// Added function to get the marriage state of the spouse contract
function getIsMarried() public view returns (bool) {
    return isMarried;
}

function getAge() public view returns (uint) {
    return age;
}

function setTaxAllowance(uint ta) public {
    require(isMarried);    // I can change the tax allowance from this function only if I'm married
    tax_allowance = ta;
    require(checkTaxAllowancePooling()); // and I also have to check if everything is alright or if ta was of
    					 // an incorrect amount
}
function getTaxAllowance() public view returns (uint) {
    return tax_allowance;
}

// Added function to verify marriage symmetry
function marriageSymmetry() view public returns (bool) {
    // Checks the symmetry iff the contract is married
    if (isMarried) {
        // Obtains spouse's contract
        Taxpayer sp = Taxpayer(address(spouse));

        // Checks if the spouse is married to the correct address and is correctly married
        return sp.getSpouse() == address(this) && sp.getIsMarried();
    }

    // If the contract is not married, the property is considered valid
    return true;
    
}

function checkTaxAllowancePooling() public view returns (bool) {
	
	if (isMarried) { // I just need this check for married people, in the code 
	        Taxpayer sp = Taxpayer(address(spouse));
		if (age >= 65) { // The first partner is over 65 years old
		 if (sp.getAge() >= 65) { // and the second one is too
	        	return tax_allowance + sp.getTaxAllowance() == ALLOWANCE_OAP * 2;
	         }
	         else { // but the second one is not
	         	return tax_allowance + sp.getTaxAllowance() == ALLOWANCE_OAP + DEFAULT_ALLOWANCE;
	         }
	        }
	        else { // The first partner is under 65 years old
	         if (sp.getAge() >= 65) { // but the second one is not
	         	return tax_allowance + sp.getTaxAllowance() == DEFAULT_ALLOWANCE + ALLOWANCE_OAP;
	         }
	         else { // Both are under 65 years old
	         	return tax_allowance + sp.getTaxAllowance() == DEFAULT_ALLOWANCE * 2;
	         }
	         
	        }
	}
	return true;
}


}

contract TestTaxpayer {

    Taxpayer Alice;
    Taxpayer Bob;
    // I copy this values to use them in the tests
     /* Constant default income tax allowance */
    uint constant  DEFAULT_ALLOWANCE = 5000;

     /* Constant income tax allowance for Older Taxpayers over 65 */
    uint constant ALLOWANCE_OAP = 7000;

    constructor() {
    	// I create two instances of the Taxpayer contract
    	Alice = new Taxpayer();
    	Bob = new Taxpayer();
    }

    function echidna_testMarriageSymmetry() public view returns (bool) {
        return Alice.marriageSymmetry();
    }

function echidna_spouseAddressNotZero() public view returns (bool) {
	if (Alice.getIsMarried()) {
		return Alice.getSpouse() != address(0); 	
	}
	return true;
}

/* OLD VERSION JUST FOR MARRIED PEOPLE
function echidna_notSelfMarried() public view returns (bool) {
	if (Alice.getIsMarried()) {
		return address(Alice) != address(Alice.getSpouse());
	}
	return true;
}
*/

// BUT THE PROPERTY SHOULD BE VERIFIED IN GENERAL
function echidna_notSelfMarried() public view returns (bool) {
	return address(Alice) != address(Alice.getSpouse());
}

function echidna_notUnderage() public view returns (bool) {
	if (Alice.getIsMarried()) {
		return Alice.getAge() >= 18;
	}
	return true;
}

/* OLD VERSION FOR THE SECOND PART 
function echidna_taxAllowancePooling() public returns (bool) {
	
	if (Alice.getIsMarried()) {
	        Taxpayer sp = Taxpayer(address(Alice.getSpouse()));
	        return Alice.getTaxAllowance() + sp.getTaxAllowance() == DEFAULT_ALLOWANCE * 2;
	}
	else {
		return Alice.getTaxAllowance == DEFAULT_ALLOWANCE;
	}
}
*/

// NEW VERSION FOR THE THIRD PART, BUT JUST FOR MARRIED PEOPLE
function echidna_taxAllowancePooling() public view returns (bool) {	
	return Alice.checkTaxAllowancePooling();
}

// I created a new test just for not-married contracts, to be more specific. This is the version
// modified for the third part
function echidna_singleTaxAllowance() public view returns (bool) {
	if (!Alice.getIsMarried()) {
		if (Alice.getAge() >= 65) {
			return Alice.getTaxAllowance() == ALLOWANCE_OAP;
		}
		else {
			return Alice.getTaxAllowance() == DEFAULT_ALLOWANCE;
		}
	}
	return true;
}
}
