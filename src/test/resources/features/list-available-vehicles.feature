Feature: List Available Vehicles
  As a customer
  I want to view a list of available vehicles ordered by price
  So that I can choose one to order

  Scenario: Successfully list available vehicles ordered by price
    Given the system has multiple vehicles available for order
    When the customer requests the list of available vehicles
    Then the system should return a list of all available vehicles ordered by price in ascending order

  Scenario: No vehicles available for order
    Given the system has no vehicles available for order
    When the customer requests the list of available vehicles
    Then the system should return an empty list
