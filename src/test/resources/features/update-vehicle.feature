Feature: Update Vehicle
  As a partner
  I want to update a vehicle in the sales service system
  So that I can modify its details

  Scenario: Successfully update an existing vehicle
    Given the partner has a valid vehicle for updating
    When the partner submits the vehicle update form
    Then the vehicle should be updated successfully

  Scenario: Fail to update a vehicle with missing required fields
    Given the partner has an invalid vehicle update form missing required fields
    When the partner submits the vehicle update form
    Then the system should reject the update with a bad request error

  Scenario: Fail to update a non-existent vehicle
    Given the partner has a valid vehicle for updating
    And the partner attempts to update a non-existent vehicle
    When the partner submits the vehicle update form
    Then the system should reject the update with a not found error
