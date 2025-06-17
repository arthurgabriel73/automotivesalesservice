Feature: Save Vehicle
  As a partner
  I want to save a vehicle in the sales service system
  So that I can allow customers to list and order it

  Scenario: Successfully save a vehicle
    Given the partner has a valid vehicle available for saving
    When the partner submits the vehicle save form
    Then the vehicle should be saved successfully

  Scenario: Fail to save a vehicle with missing required fields
    Given the partner has an invalid vehicle save form missing required fields
    When the partner submits the vehicle save form
    Then the system should reject the save with a bad request error
