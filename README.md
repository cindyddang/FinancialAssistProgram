# FinancialAssistProgram

## Description

This is a program I made during my senior of high school for my Internal Assessment, one of the requirement from IB curriculum. 

This program aims to help people within one household, who always hang out together, can keep track on the money spent so that they could pay each other after a desired time period.

## Functions

1. Keep track on basic information of spendings from the households, including how much money was spent, who were responsible for the spending,...
* Adding, deleting, Editing information
* Monthly reports, yearly reports available
2. Calculating the amount of money one specific person is reponsible for 
* Display detail of the money
4. Suggest which person should pay which person with how much
5. Give alert whenever spending exceeds predetermined limit.

## Installation

**1. Run from jar files**

Go to product directory. Run the product by double clicking on Instal.jar. After a database folder is created, double click on financialAssistant.jar to run the product.

**2. Run from terminal**

- Use terminal, go to product directory by typing:

cd Pro*

- Instaling database by typing:

java -jar Instal.jar

- Running product by typing:
 
java -jar financialAssistant.jar

## Usage

The program needs at least a name and a limit to make it works. When first using program, add name and limit into database by:

- Enter a name into name field and click add person.
- Enter a limit number into limit field and click set limit.

Insert function can be used when all textfields are entered correctly and at least two checkboxes are checked. 

Update and delete function works when order textfield is entered. Order is based on order column in Spending History.

All display buttons will display spending events when already analyzed.
