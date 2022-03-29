Feature: Create Lead

Background:
Given A Chrome browser is launched
And Load the Leaftaps url 'http://leaftaps.com/opentaps/control/main'
And Maximize the browser

Scenario Outline: TC001 Create Lead

Given Enter the username as <username>
And Enter the password as <password>
When The Login button is clicked
Then The Welcome Page should be displayed
And Click on CRMSFA link
And Click on leads link
And Click on create lead link
And Enter the company name as <companyname>
And Enter the first name as <firstname>
And Enter the last name as <lastname>
When The submit button is clicked
Then The lead should be created
Examples:
|username|password|companyname|firstname|lastname|
|'Demosalesmanager'|'crmsfa'|'TestLeaf'|'Hari'|'R'|