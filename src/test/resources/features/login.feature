Feature: User Login

  Scenario: Login and add a product to the cart
    Given I login to webpage
    Then I click on product
    Then I add product to the cart
    Then log off
