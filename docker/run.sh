#!/bin/sh
set -e

exec java \
  -Dspring.profiles.active=production \
  -Dspring.config.additional-location=file:/app/config/ \
  -jar /app/wedding-invitation.jar