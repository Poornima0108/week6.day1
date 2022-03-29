Feature: Edit Lead

Background:
Given A Chrome browser is launched
And Load the Leaftaps url 'http://leaftaps.com/opentaps/control/main'
And Maximize the browser

Scenario Outline: TC004 Edit Lead

Given Enter the username as <username>
And Enter the password as <password>
When The Login button is clicked
Then The Welcome Page should be displayed
And Click on CRMSFA link
And Click on leads link
And Click on find leads link
And Enter the phone number as <phonenumber>
And Click on find leads button
Then The lead is updated
Examples:
|username|password|phonenumber|
|'Demosalesmanager'|'crmsfa'|'9'|