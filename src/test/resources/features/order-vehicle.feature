Feature: Order Vehicle
  As a customer
  I want to order a vehicle
  So that I can purchase it

  Scenario: Successfully order a vehicle
    Given the system has a valid vehicle available for order
    And the customer has a valid vehicle order form
    When the customer submits the vehicle order form
    Then the vehicle should be ordered successfully

  Scenario: Fail to order a vehicle with missing required fields
    Given the system has an invalid vehicle order form missing required fields
    When the customer submits the vehicle order form
    Then the system should reject the order with a bad request error

  Scenario: Fail to order a vehicle that is out of stock
    Given the system has a vehicle that is out of stock
    And the customer has a valid vehicle order form
    When the customer attempts to order the vehicle
    Then the system should reject the order with an out of stock error

  Scenario: Fail to order a non-existent vehicle
    Given the customer has a non-existent vehicle Id
    When the customer attempts to order the vehicle
    Then the system should reject the order with a not found error
