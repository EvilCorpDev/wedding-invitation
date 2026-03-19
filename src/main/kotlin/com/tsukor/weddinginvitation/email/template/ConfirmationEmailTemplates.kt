package com.tsukor.weddinginvitation.email.template

import com.tsukor.weddinginvitation.enums.Lang

object ConfirmationEmailTemplates {

    fun getSubject(lang: Lang) = when(lang) {
        Lang.EN -> SUBJECT_EN
        Lang.UA -> SUBJECT_UA
        Lang.PL -> SUBJECT_PL
    }

    fun getText(lang: Lang) = when(lang) {
        Lang.UA -> TEXT_UA
        Lang.PL -> TEXT_PL
        Lang.EN -> TEXT_EN
    }

    fun getHTML(lang: Lang) = when(lang) {
        Lang.UA -> HTML_UA
        Lang.PL -> HTML_PL
        Lang.EN -> HTML_EN
    }

    const val SUBJECT_EN = "Please confirm your email — {{COUPLE_NAMES}}"
    const val SUBJECT_UA = "Будь ласка, підтвердіть свою електронну адресу — {{COUPLE_NAMES}}"
    const val SUBJECT_PL = "Potwierdź swój adres e-mail — {{COUPLE_NAMES}}"

    const val TEXT_EN = """
Hi {{GUEST_NAME}},

Please confirm your email address for updates about the wedding of {{COUPLE_NAMES}}.

Confirm link:
{{CONFIRM_URL}}

This link will expire in {{EXPIRES_IN}}.
If you didn’t request this, you can ignore this email.

— {{COUPLE_NAMES}}
{{WEDDING_DOMAIN}}
"""

    const val TEXT_PL = """
Cześć {{GUEST_NAME}},

Prosimy o potwierdzenie adresu e-mail, aby otrzymywać informacje o ślubie {{COUPLE_NAMES}}.

Link potwierdzający:
{{CONFIRM_URL}}

Link wygaśnie za {{EXPIRES_IN}}.
Jeśli to nie Ty wysłałeś tę prośbę, możesz zignorować tę wiadomość.

— {{COUPLE_NAMES}}
{{WEDDING_DOMAIN}}
"""

    const val TEXT_UA = """
Привіт, {{GUEST_NAME}}!

Будь ласка, підтвердіть свою електронну адресу, щоб отримувати новини про весілля {{COUPLE_NAMES}}.

Посилання для підтвердження:
{{CONFIRM_URL}}

Посилання дійсне {{EXPIRES_IN}}.
Якщо ви не надсилали цей запит, просто проігноруйте цей лист.

— {{COUPLE_NAMES}}
{{WEDDING_DOMAIN}}
"""



    const val HTML_EN = """
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Confirm your email</title>
  </head>
  <body style="margin:0; padding:0; background:#f6f7fb; font-family: Arial, Helvetica, sans-serif; color:#111827;">
    <div style="max-width:640px; margin:0 auto; padding:24px;">
      <div style="background:#ffffff; border:1px solid #e5e7eb; border-radius:16px; overflow:hidden;">
        <div style="padding:24px 24px 12px 24px;">
          <h1 style="margin:0 0 8px 0; font-size:20px; line-height:28px;">
            Confirm your email address
          </h1>
          <p style="margin:0; font-size:14px; line-height:22px; color:#4b5563;">
            This helps {{COUPLE_NAMES}} send you updates about the wedding.
          </p>
        </div>

        <div style="padding:12px 24px 24px 24px;">
          <p style="margin:0 0 16px 0; font-size:14px; line-height:22px;">
            Hi {{GUEST_NAME}},<br/><br/>
            Please confirm your email by clicking the button below:
          </p>

          <div style="margin:20px 0;">
            <a href="{{CONFIRM_URL}}"
               style="display:inline-block; background:#111827; color:#ffffff; text-decoration:none; padding:12px 18px; border-radius:12px; font-size:14px; font-weight:600;">
              Confirm email
            </a>
          </div>

          <p style="margin:0 0 12px 0; font-size:13px; line-height:20px; color:#4b5563;">
            If the button doesn’t work, copy and paste this link into your browser:
          </p>

          <p style="margin:0; font-size:13px; line-height:20px; word-break:break-all;">
            <a href="{{CONFIRM_URL}}" style="color:#2563eb; text-decoration:underline;">{{CONFIRM_URL}}</a>
          </p>

          <p style="margin:20px 0 0 0; font-size:12px; line-height:18px; color:#6b7280;">
            This link will expire in {{EXPIRES_IN}} hours. If you didn’t request this, you can ignore this email.
          </p>
        </div>

        <div style="padding:16px 24px; background:#f9fafb; border-top:1px solid #e5e7eb; font-size:12px; color:#6b7280;">
          Sent by {{COUPLE_NAMES}} • {{WEDDING_DOMAIN}}
        </div>
      </div>
    </div>
  </body>
</html>
"""

    const val HTML_UA = """
<!doctype html>
<html lang="uk">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Підтвердіть свою електронну адресу</title>
  </head>
  <body style="margin:0; padding:0; background:#f6f7fb; font-family: Arial, Helvetica, sans-serif; color:#111827;">
    <div style="max-width:640px; margin:0 auto; padding:24px;">
      <div style="background:#ffffff; border:1px solid #e5e7eb; border-radius:16px; overflow:hidden;">
        <div style="padding:24px 24px 12px 24px;">
          <h1 style="margin:0 0 8px 0; font-size:20px; line-height:28px;">
            Підтвердіть свою електронну адресу
          </h1>
          <p style="margin:0; font-size:14px; line-height:22px; color:#4b5563;">
            Це допоможе {{COUPLE_NAMES}} надсилати вам новини про весілля.
          </p>
        </div>

        <div style="padding:12px 24px 24px 24px;">
          <p style="margin:0 0 16px 0; font-size:14px; line-height:22px;">
            Привіт, {{GUEST_NAME}}!<br/><br/>
            Будь ласка, підтвердіть свою електронну адресу, натиснувши кнопку нижче:
          </p>

          <div style="margin:20px 0;">
            <a href="{{CONFIRM_URL}}"
               style="display:inline-block; background:#111827; color:#ffffff; text-decoration:none; padding:12px 18px; border-radius:12px; font-size:14px; font-weight:600;">
              Підтвердити пошту
            </a>
          </div>

          <p style="margin:0 0 12px 0; font-size:13px; line-height:20px; color:#4b5563;">
            Якщо кнопка не працює, скопіюйте та вставте це посилання у браузер:
          </p>

          <p style="margin:0; font-size:13px; line-height:20px; word-break:break-all;">
            <a href="{{CONFIRM_URL}}" style="color:#2563eb; text-decoration:underline;">{{CONFIRM_URL}}</a>
          </p>

          <p style="margin:20px 0 0 0; font-size:12px; line-height:18px; color:#6b7280;">
            Посилання дійсне {{EXPIRES_IN}} годин. Якщо ви не надсилали цей запит, просто проігноруйте цей лист.
          </p>
        </div>

        <div style="padding:16px 24px; background:#f9fafb; border-top:1px solid #e5e7eb; font-size:12px; color:#6b7280;">
          Надіслано від {{COUPLE_NAMES}} • {{WEDDING_DOMAIN}}
        </div>
      </div>
    </div>
  </body>
</html>
"""

    const val HTML_PL = """
<!doctype html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Potwierdź swój adres e-mail</title>
  </head>
  <body style="margin:0; padding:0; background:#f6f7fb; font-family: Arial, Helvetica, sans-serif; color:#111827;">
    <div style="max-width:640px; margin:0 auto; padding:24px;">
      <div style="background:#ffffff; border:1px solid #e5e7eb; border-radius:16px; overflow:hidden;">
        <div style="padding:24px 24px 12px 24px;">
          <h1 style="margin:0 0 8px 0; font-size:20px; line-height:28px;">
            Potwierdź swój adres e-mail
          </h1>
          <p style="margin:0; font-size:14px; line-height:22px; color:#4b5563;">
            Dzięki temu {{COUPLE_NAMES}} będą mogli przesyłać Ci aktualności dotyczące ślubu.
          </p>
        </div>

        <div style="padding:12px 24px 24px 24px;">
          <p style="margin:0 0 16px 0; font-size:14px; line-height:22px;">
            Cześć {{GUEST_NAME}},<br/><br/>
            Potwierdź swój adres e-mail, klikając przycisk poniżej:
          </p>

          <div style="margin:20px 0;">
            <a href="{{CONFIRM_URL}}"
               style="display:inline-block; background:#111827; color:#ffffff; text-decoration:none; padding:12px 18px; border-radius:12px; font-size:14px; font-weight:600;">
              Potwierdź e-mail
            </a>
          </div>

          <p style="margin:0 0 12px 0; font-size:13px; line-height:20px; color:#4b5563;">
            Jeśli przycisk nie działa, skopiuj i wklej ten link do przeglądarki:
          </p>

          <p style="margin:0; font-size:13px; line-height:20px; word-break:break-all;">
            <a href="{{CONFIRM_URL}}" style="color:#2563eb; text-decoration:underline;">{{CONFIRM_URL}}</a>
          </p>

          <p style="margin:20px 0 0 0; font-size:12px; line-height:18px; color:#6b7280;">
            Link wygaśnie za {{EXPIRES_IN}} godzin. Jeśli to nie Ty wysłałeś tę prośbę, możesz zignorować tę wiadomość.
          </p>
        </div>

        <div style="padding:16px 24px; background:#f9fafb; border-top:1px solid #e5e7eb; font-size:12px; color:#6b7280;">
          Wysłane przez {{COUPLE_NAMES}} • {{WEDDING_DOMAIN}}
        </div>
      </div>
    </div>
  </body>
</html>
"""
}