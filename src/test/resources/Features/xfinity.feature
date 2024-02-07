Feature: Test xfinity.com website functionalities
  Background:
    Given User is on the xfinity.com homepage

  @positive_test
  Scenario: User tests the search functionality for a valid content
    When User uses the search icon to look for a specific content
    And User clicks on search bar
    Then The search results should be relevant and displayed accurately

  @negative_test
  Scenario: User tests the search functionality for an invalid content
    When User uses the search icon to look for a invalid content
    And User clicks on search bar
    Then The search results should be irrelevant

  @negative_test
  Scenario: User checks login is unsuccessful with invalid username
    When User clicks the Sign In link
    And User enters username "tanzin" and clicks Let's go button
    Then User is failed to login

  @positive_test
  Scenario: User navigates to the Internet Deals page
    When User hover overs the mouse on Internet link
    And User clicks on Internet Deals
    Then User should lead to the corresponding page without errors

  @positive_test
  Scenario: User checks for service availability
    When User hover overs the mouse on Find a Store icon and clicks on the link
    And User enters the address in the service availability checker and clicks it
    Then User should see information about the available location in the area

  @positive_test
  Scenario: User navigates to the Career webpage
    When User clicks on Careers link
    Then User should lead to the corresponding webpage

  @positive_test
  Scenario: User navigates to Accessibility page
    When User clicks on Sitemap
    And User clicks on Customer Service
    And User clicks on Accessibility link
    Then User navigates to Accessibility page

  @positive_test
  Scenario: Validate service in my area
    When User clicks on Service in my Area link
    And User clicks on Illinois link
    And User clicks on Vernon Hills link
    Then User navigates to Internet Services Vernon Hills page

  @positive_test
  Scenario: User checks login is successful with valid username and password
    When User clicks the Sign In link
    And User enters Email and clicks Let's go button
    And User enters Password and clicks sign in
    Then User should be successfully logged in

  @negative_test
  Scenario: User checks for service availability with invalid address
    When User hover overs the mouse on Find a Store icon and clicks on the link
    And User enters the invalid address in the service availability checker and clicks it
    Then The search results should be irrelevant for the current address














