Feature: Merge Lead

Background:
Given A Chrome browser is launched
And Load the Leaftaps url 'http://leaftaps.com/opentaps/control/main'
And Maximize the browser

Scenario Outline: TC005 Merge Lead

Given Enter the username as <username>
And Enter the password as <password>
When The Login button is clicked
Then The Welcome Page should be displayed
And Click on CRMSFA link
And Click on leads link
And Click on merge leads link
And Merge the leads <fname> <mergename>
Then Verify leads are merged
Examples:
|username|password|fname|mergename|
|'Demosalesmanager'|'crmsfa'|'g'|'babu'|