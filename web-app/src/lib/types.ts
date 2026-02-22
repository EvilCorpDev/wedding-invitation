export type ApiEvent = {
  coupleNames: string;
  dateIso: string; // YYYY-MM-DD
  venueName?: string;
  venueAddress?: string;
  scheduleText?: string;
  dressCode?: string;
  extraInfo?: string;
};

export type ValidateResponse =
  | {
      ok: true;
      registrationToken: string;
      event: ApiEvent;
      alreadySubmitted?: boolean;
      guestPreview?: { firstName: string; lastName: string };
    }
  | { ok: false; message: string };

export type SubmitResponse = { ok: true } | { ok: false; message: string };
