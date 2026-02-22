npm i# Wedding Invitation Frontend (React + Vite + TS)

A small web app for wedding invitations:
- Guests open a link, enter an invitation code
- Fill in their contact details
- Data is sent to your backend (Kotlin/Spring) and stored for later notifications

## Tech
- Vite + React + TypeScript
- TailwindCSS (simple local UI components)
- react-hook-form + zod validation
- PL / UA / EN language toggle (stored in localStorage)

## Setup
```bash
npm install
cp .env.example .env
npm run dev
```

Open: http://localhost:5173

## Backend API contract
### 1) Validate invitation code
`POST /api/registration/validate`
Body:
```json
{ "code": "YOUR-CODE" }
```
Response (200):
```json
{
  "ok": true,
  "registrationToken": "opaque-token",
  "event": {
    "coupleNames": "Alice & Bob",
    "dateIso": "2026-07-11",
    "venueName": "Venue",
    "venueAddress": "Street 1\nCity",
    "scheduleText": "16:00 ceremony\n18:00 dinner",
    "dressCode": "Smart casual",
    "extraInfo": "Any extra info"
  },
  "alreadySubmitted": false
}
```
Error (4xx):
```json
{ "ok": false, "message": "Invalid code" }
```

### 2) Submit guest details
`POST /api/registration/submit`
Body:
```json
{
  "registrationToken": "opaque-token",
  "guest": { "firstName": "Anna", "lastName": "Kowalska", "phone": "+48600000000", "email": "anna@example.com" },
  "consent": { "terms": true, "marketing": true }
}
```
Response:
```json
{ "ok": true }
```

## Notes
- Prefer returning `registrationToken` instead of continuing to use the raw code.
- Put your app behind HTTPS in production.
