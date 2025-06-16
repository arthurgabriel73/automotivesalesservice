Feature: List Sold Vehicles
  As a partner
  I want to list sold vehicles in the sales service system
  So that I can view the vehicles that have been sold

  Scenario: Successfully list all sold vehicles
    Given the system has multiple sold vehicles
    When the partner requests to list all sold vehicles
    Then the system should return a list of all sold vehicles


  Scenario: No sold vehicles available
    Given the system has no sold vehicles
    When the partner requests to list all sold vehicles
    Then the system should return an empty list
