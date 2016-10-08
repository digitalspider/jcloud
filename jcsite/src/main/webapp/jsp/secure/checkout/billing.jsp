<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/jsp/include/taglibs.jsp"%>

<s:layout-render name="/jsp/layout/secure.jsp" pageTitle="Billing">

	<s:layout-component name="customjs">
<script src="${ctx}/js/jquery.payment.js"></script>
<script type="text/javascript" src="https://js.stripe.com/v2/"></script>
<script type="text/javascript">
  Stripe.setPublishableKey('pk_test_ecxPFdQHDEFk3Jn3GZpVO4Lj');
</script>
<script>
function stripeResponseHandler(status, response) {
  // Grab the form:
  var $form = $('#paymentForm');

  if (response.error) {
    $form.find('.payment-errors').text(response.error.message);
    $form.find('.submit').prop('disabled', false); // Re-enable submission
  } else {
    // Get the token ID:
    var token = response.id;

    // Insert the token ID into the form so it gets submitted to the server:
    $form.append($('<input type="hidden" name="stripeToken">').val(token));

    // Submit the form:
    $form.get(0).submit();
  }
};
</script>

  <style type="text/css" media="screen">
    .has-error input {
      border-width: 2px;
    }
    .validation.text-danger:after {
      content: 'Validation failed';
    }
    .validation.text-success:after {
      content: 'Validation passed';
    }
  </style>

<script>
    jQuery(function($) {
      $('[data-numeric]').payment('restrictNumeric');
      $('.cc-number').payment('formatCardNumber');
      $('.cc-exp').payment('formatCardExpiry');
      $('.cc-cvc').payment('formatCardCVC');
      $.fn.toggleInputError = function(erred) {
        this.parent('.form-group').toggleClass('has-error', erred);
        return this;
      };
      $('#paymentForm').submit(function(e) {
        e.preventDefault();
        var cardType = $.payment.cardType($('.cc-number').val());
        $('.cc-number').toggleInputError(!$.payment.validateCardNumber($('.cc-number').val()));
        $('.cc-exp').toggleInputError(!$.payment.validateCardExpiry($('.cc-exp').payment('cardExpiryVal')));
        $('.cc-cvc').toggleInputError(!$.payment.validateCardCVC($('.cc-cvc').val(), cardType));
        $('.cc-brand').text(cardType);
        $('.validation').removeClass('text-danger text-success');
        $('.validation').addClass($('.has-error').length ? 'text-danger' : 'text-success');

        if ($('.has-error').length>0) {
            var expiry = $('.cc-exp').val().split('/');
            $('#cc-exp-month').val(expiry[0].trim());
            $('#cc-exp-year').val(expiry[1].trim());
            var $form = $('#paymentForm');
            // Disable the submit button to prevent repeated clicks:
            $form.find('.submit').prop('disabled', true);

            // Request a token from Stripe:
			Stripe.card.createToken($form, stripeResponseHandler);
        }
        // Prevent the form from being submitted:
        return false;
      });
    });
</script>

    </s:layout-component>

    <s:layout-component name="contents">

        <section class="tm-section">
            <div class="container-fluid">
                <div class="row">

					<s:form id="paymentForm" method="post" action="/secure/checkout/purchase" class="tm-payment-form">
                    	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                        	<s:errors globalErrorsOnly="true"/>
                            <div class="h4">Billing Details</div>
                            <div class="form-group">
                            	<s:errors field="name"/>
                                <input type="text" id="name" name="name" class="form-control" placeholder="Name" required="true" value="${actionBean.name}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="address"/>
                                <input type="text" id="address" name="address" class="form-control" placeholder="Street Address" required="true" value="${actionBean.address.address}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="city"/>
                                <input type="text" id="city" name="city" class="form-control" placeholder="Suburb" required="true" value="${actionBean.address.city}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="state"/>
                                <input type="text" id="state" name="state" class="form-control" placeholder="State" required="true" value="${actionBean.address.state}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="postcode"/>
                                <input type="text" id="postcode" name="postcode" class="form-control" placeholder="Postcode" required="true" value="${actionBean.address.postcode}"/>
                            </div>

						</div>
						<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-6">

                            <div class="h4">Payment Details</div>
							<input id="cc-exp-month" data-stripe="exp_month" type="hidden"/>
							<input id="cc-exp-year" data-stripe="exp_year" type="hidden"/>

<div id="validation" name="validation"></div>
<div id="payment-errors" name="payment-errors"></div>

                            <div id="price" class="control-label">Price <span class="text-muted price">$12.99</span></div>

                            <div class="form-group">
                            	<s:errors field="cc-number"/>
                            	<label for="cc-number" class="control-label">Card number <small class="text-muted">[<span class="cc-brand">${actionBean.creditCard.type}</span>]</small></label>
                                <input type="tel" id="cc-number" data-stripe="number" class="form-control cc-number" placeholder="1234 5678 9012 3456" required="true" value="${actionBean.creditCard.cardSuffix}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="cc-address"/>
                            	<label for="cc-exp" class="control-label">Card expiry </label>
                                <input type="tel" id="cc-exp" class="form-control cc-exp" autocomplete="cc-exp" placeholder="01 / 16" required="true" value="${actionBean.creditCard.expiry}"/>
                            </div>
                            <div class="form-group">
                            	<s:errors field="cc-cvc"/>
                            	<label for="cc-cvc" class="control-label">Card CVC </label>
                                <input type="tel" id="cc-cvc" data-stripe="cvc" class="form-control cc-cvc" auto-complete="off" placeholder="111" required="true"/>
                            </div>
                            <s:submit id="purchase" name="purchase" value="buy now" class="tm-btn text-uppercase" />

                    	</div>
                    </s:form>

                </div>
                
            </div>
        </section>

    </s:layout-component>
</s:layout-render>