import React from "react";
import { Button } from "./ui/Button";
import type { Lang } from "@/lib/i18n";

export function LanguageToggle({ lang, setLang }: { lang: Lang; setLang: (l: Lang) => void }) {
  const item = (l: Lang, label: string) => (
    <Button
      type="button"
      size="sm"
      variant={lang === l ? "solid" : "outline"}
      onClick={() => setLang(l)}
    >
      {label}
    </Button>
  );

  return (
    <div className="flex items-center gap-2">
      {item("en", "EN")}
      {item("pl", "PL")}
      {item("ua", "UA")}
    </div>
  );
}
