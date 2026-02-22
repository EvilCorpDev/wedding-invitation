import React, { useMemo } from "react";
import { PartyPopper } from "lucide-react";
import type { ApiEvent } from "@/lib/types";
import type { Lang } from "@/lib/i18n";
import { LanguageToggle } from "./LanguageToggle";

export function Header({
  event,
  lang,
  setLang,
  t,
}: {
  event: ApiEvent | null;
  lang: Lang;
  setLang: (l: Lang) => void;
  t: (k: string) => string;
}) {
  const title = useMemo(() => {
    if (event?.coupleNames) return `${event.coupleNames} — ${t("titleFallback")}`;
    return t("titleFallback");
  }, [event, t]);

  return (
    <header className="mb-6">
      <div className="flex items-start justify-between gap-4">
        <div className="flex items-center gap-3">
          <div className="h-10 w-10 rounded-2xl border border-slate-200 flex items-center justify-center bg-white">
            <PartyPopper className="h-5 w-5" />
          </div>
          <div>
            <h1 className="text-2xl font-semibold leading-tight">{title}</h1>
            <p className="text-sm text-slate-500">{t("subtitle")}</p>
          </div>
        </div>

        <div className="flex flex-col items-end gap-2">
          <div className="text-xs text-slate-500">{t("language")}</div>
          <LanguageToggle lang={lang} setLang={setLang} />
        </div>
      </div>
    </header>
  );
}
