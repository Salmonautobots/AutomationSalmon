package com.salmon.test.step_definitions.mobile;

import com.salmon.test.models.cucumber.AddressModel;
import com.salmon.test.models.cucumber.CardDeatilsModel;
import com.salmon.test.models.cucumber.CreditCardModel;
import com.salmon.test.page_objects.mobile.*;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class CheckoutSteps {

    MobilePDPPage mobilePDPPage;
    FullCartPage fullCartPage;
    BillingAddressPage billingAddressPage;
    MyAccountsPage myAccountsPage;
    ShippingAddressPage shippingAddressPage;
    PickAPathPage pickAPathPage;
    CreateAccountPage createAccountPage;
    OrderConfirmationPage orderConfirmationPage;


    public CheckoutSteps(MobilePDPPage mobilePDPPage, FullCartPage fullCartPage, BillingAddressPage billingAddressPage, MyAccountsPage myAccountsPage, ShippingAddressPage shippingAddressPage, PickAPathPage pickAPathPage, CreateAccountPage createAccountPage, OrderConfirmationPage orderConfirmationPage) {

        this.mobilePDPPage = mobilePDPPage;
        this.fullCartPage = fullCartPage;
        this.billingAddressPage = billingAddressPage;
        this.myAccountsPage = myAccountsPage;
        this.shippingAddressPage = shippingAddressPage;
        this.pickAPathPage = pickAPathPage;
        this.createAccountPage = createAccountPage;
        this.orderConfirmationPage = orderConfirmationPage;
    }

    @When("^navigate to Full Cart page$")
    public void navigate_to_Full_Cart_page() throws Exception {
        mobilePDPPage.clickViewCart();

    }

    @And("^I click on the Checkout button from the full cart$")
    public void iClickOnTheCheckoutButtonFromTheFullCart() throws Exception {
        fullCartPage.clickCheckoutButton();
    }

    @And("^select the credit card from the option$")
    public void selectTheCreditCardFromTheOption() throws Exception {
        shippingAddressPage.checkUseThisAddressForBilingCheckBox();
        shippingAddressPage.selectContinueToBillingButton();
        billingAddressPage.selectCreditCard();
    }

    @When("^login with an existing credentials on the pick a path page$")
    public void login_with_an_existing_credentials_on_the_pick_a_path_page() throws Exception {
        myAccountsPage.loginWithExistingUser();
    }

    @When("^I click on create account Now button and register as a new user$")
    public void i_click_on_create_account_Now_button_and_register_as_a_new_user(DataTable userDetails) throws Exception {
        pickAPathPage.clickCreateAccountNow();
        createAccountPage.registerUser(userDetails);
    }

    @And("^enter shipping address$")
    public void enterShippingAddress(List<AddressModel> addressModel1) throws Exception {

        for (AddressModel addressModel : addressModel1) {
            shippingAddressPage.enterDataModelNewUserDetails(addressModel);
        }

        shippingAddressPage.checkUseThisAddressForBilingCheckBox();
        shippingAddressPage.selectContinueToBillingButton();
    }

    @And("^enter credit card details on the billing address page and click continue$")
    public void enterCreditCardDetailsOnTheBillingAddressPageAndClickContinue(List<CreditCardModel> creditCardModels) throws Exception {

        for (CreditCardModel creditCardModel : creditCardModels) {
            billingAddressPage.enterCreditCardDetails1(creditCardModel);
        }
        billingAddressPage.clickPlaceOrder();
    }

    @Then("^the order should be successfully placed$")
    public void theOrderShouldBeSuccessfullyPlaced() throws Exception {
        orderConfirmationPage.orderNumberPrint();
    }

    @And("^click Checkout as Guest button$")
    public void clickCheckoutAsGuestButton() throws Exception {
        pickAPathPage.clickCheckoutAsGuestButton();
    }

    @And("^enter cvn code$")
    public void ienter_cvn_code() throws Exception {
        billingAddressPage.placeOrderFromCheckoutPage();
        billingAddressPage.clickPlaceOrder();
    }

    @And("^enter email address$")
    public void enterEmailAddress(DataTable email) throws Exception {
        billingAddressPage.enterEmailAddress(email);
    }
}