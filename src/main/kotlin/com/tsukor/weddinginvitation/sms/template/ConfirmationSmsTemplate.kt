package com.tsukor.weddinginvitation.sms.template

import com.tsukor.weddinginvitation.enums.Lang

object ConfirmationSmsTemplate {
    fun getSmsText(lang: Lang) = when(lang) {
        Lang.EN -> TEXT_EN
        Lang.UA -> TEXT_UA
        Lang.PL -> TEXT_PL
    }

    const val TEXT_EN = "Please confirm you phone number {{CONFIRM_URL}}"
    const val TEXT_UA = "Будь ласка, підтвердіть свій номер телефону {{CONFIRM_URL}}"
    const val TEXT_PL = "Potwierdź swój numer telefonu {{CONFIRM_URL}}"
}