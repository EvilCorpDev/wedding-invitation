import React, { useEffect, useMemo, useState } from "react";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/Alert";
import { Header } from "@/components/Header";
import { EventDetailsCard } from "@/components/EventDetailsCard";
import StickerBackground from "@/components/StickerBackground";
import { useLocalStorageState } from "@/lib/storage";
import { apiPost } from "@/lib/api";
import type { ApiEvent, SubmitResponse, ValidateResponse } from "@/lib/types";
import { I18N, type Lang } from "@/lib/i18n";
import { CodeStep } from "@/features/registration/CodeStep";
import { GuestFormStep } from "@/features/registration/GuestFormStep";
import { SuccessStep } from "@/features/registration/SuccessStep";
import type { CodeForm, GuestForm } from "@/features/registration/schemas";

type Step = "code" | "form" | "success";

export default function App() {
  const [lang, setLang] = useLocalStorageState<Lang>("wedding.lang", "en");
  const t = useMemo(() => {
    const dict = I18N[lang] || I18N.en;
    return (key: string) => dict[key] ?? I18N.en[key] ?? key;
  }, [lang]);

  const [step, setStep] = useState<Step>("code");
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const [registrationToken, setRegistrationToken] = useLocalStorageState<string | null>(
      "wedding.registrationToken",
      null
  );
  const [event, setEvent] = useLocalStorageState<ApiEvent | null>("wedding.event", null);
  const [alreadySubmitted, setAlreadySubmitted] = useLocalStorageState<boolean>(
      "wedding.alreadySubmitted",
      false
  );

  useEffect(() => {
    if (registrationToken && event) {
      setStep(alreadySubmitted ? "success" : "form");
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  async function handleValidate(values: CodeForm) {
    setError(null);
    setBusy(true);
    try {
      const data = await apiPost<ValidateResponse>("/api/registration/validate", {
        code: values.code,
      });``

      if (!data.ok) {
        setError(data.message);
        return;
      }

      setRegistrationToken(data.registrationToken);
      setEvent(data.event);
      setAlreadySubmitted(Boolean(data.alreadySubmitted));
      setStep(data.alreadySubmitted ? "success" : "form");
    } catch (e: any) {
      setError(e?.message || t("genericError"));
    } finally {
      setBusy(false);
    }
  }

  async function handleSubmit(values: GuestForm) {
    if (!registrationToken) {
      setError(t("missingToken"));
      setStep("code");
      return;
    }

    setError(null);
    setBusy(true);
    try {
      const data = await apiPost<SubmitResponse>("/api/registration/submit", {
        registrationToken,
        guest: {
          firstName: values.firstName,
          lastName: values.lastName,
          phone: values.phone,
          email: values.email,
        },
        consent: {
          terms: values.consentTerms,
        },
        lang: lang,
      });

      if (!data.ok) {
        setError(data.message);
        return;
      }

      setAlreadySubmitted(true);
      setStep("success");
    } catch (e: any) {
      setError(e?.message || t("genericError"));
    } finally {
      setBusy(false);
    }
  }

  function resetAll() {
    setError(null);
    setBusy(false);
    setRegistrationToken(null);
    setEvent(null);
    setAlreadySubmitted(false);
    setStep("code");
    try {
      localStorage.removeItem("wedding.registrationToken");
      localStorage.removeItem("wedding.event");
      localStorage.removeItem("wedding.alreadySubmitted");
    } catch {
      // ignore
    }
  }

  return (
      <div className="relative min-h-screen w-full overflow-hidden bg-slate-50">
        <StickerBackground />

        <div className="relative z-10 mx-auto max-w-2xl px-4 py-10">
          <Header event={event} lang={lang} setLang={setLang} t={t} />

          {error && (
              <Alert className="mb-4" variant="destructive">
                <AlertTitle>{t("oops")}</AlertTitle>
                <AlertDescription>{error}</AlertDescription>
              </Alert>
          )}

          {event && <EventDetailsCard event={event} t={t} />}

          {step === "code" && <CodeStep busy={busy} onValidate={handleValidate} t={t} />}

          {step === "form" && (
              <GuestFormStep
                  busy={busy}
                  onSubmit={handleSubmit}
                  onReset={resetAll}
                  t={t}
              />
          )}

          {step === "success" && <SuccessStep onReset={resetAll} t={t} />}

          <footer className="mt-8 text-center text-xs text-slate-500">
            <p>{t("footer")}</p>
          </footer>
        </div>
      </div>
  );
}