package com.tota.clients.notification;

public record NotificationRequest(Integer toCustomerId, String toCustomerName, String message, String toCustomerEmail) {

}
