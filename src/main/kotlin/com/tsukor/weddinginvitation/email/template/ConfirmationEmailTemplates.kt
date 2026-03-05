package com.tsukor.weddinginvitation.email.template

object ConfirmationEmailTemplates {

    const val SUBJECT = "Please confirm your email — {{COUPLE_NAMES}}"

    const val TEXT = """
Hi {{GUEST_NAME}},

Please confirm your email address for updates about the wedding of {{COUPLE_NAMES}}.

Confirm link:
{{CONFIRM_URL}}

This link will expire in {{EXPIRES_IN}}.
If you didn’t request this, you can ignore this email.

— {{COUPLE_NAMES}}
{{WEDDING_DOMAIN}}
"""

    const val HTML = """
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
            This link will expire in {{EXPIRES_IN}}. If you didn’t request this, you can ignore this email.
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
}