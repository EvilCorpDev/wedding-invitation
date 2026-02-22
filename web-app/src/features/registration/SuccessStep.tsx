import React from "react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/Card";
import { Button } from "@/components/ui/Button";

export function SuccessStep({ onReset, t }: { onReset: () => void; t: (k: string) => string }) {
  return (
    <Card>
      <CardHeader>
        <CardTitle>{t("thanksTitle")}</CardTitle>
        <CardDescription>{t("thanksDesc")}</CardDescription>
      </CardHeader>
      <CardContent className="space-y-4">
        <div className="rounded-2xl border border-slate-200 p-4 text-sm">
          <div className="font-medium">{t("nextTitle")}</div>
          <ul className="mt-2 list-disc pl-5 space-y-1 text-slate-500">
            <li>{t("next1")}</li>
            <li>{t("next2")}</li>
            <li>{t("next3")}</li>
          </ul>
        </div>
        <div className="flex flex-col gap-2 sm:flex-row">
          <Button type="button" className="sm:flex-1" onClick={() => window.location.reload()}>
            {t("refresh")}
          </Button>
          <Button type="button" variant="outline" className="sm:w-40" onClick={onReset}>
            {t("anotherCode")}
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}
