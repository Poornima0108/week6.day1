Feature: Delete Lead

Background:
Given A Chrome browser is launched
And Load the Leaftaps url 'http://leaftaps.com/opentaps/control/main'
And Maximize the browser

Scenario Outline: TC002 Delete Lead

Given Enter the username as <username>
And Enter the password as <password>
When The Login button is clicked
Then The Welcome Page should be displayed
And Click on CRMSFA link
And Click on leads link
And Click on find leads link
And Enter the phone number as <phonenumber>
And Click on find leads button
And Delete the lead
Then Verify lead is deleted
Examples:
|username|password|phonenumber|
|'Demosalesmanager'|'crmsfa'|'9'|