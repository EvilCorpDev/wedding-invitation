import React from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Loader2, ShieldCheck } from "lucide-react";

import { codeSchema, type CodeForm } from "./schemas";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/Card";
import { Button } from "@/components/ui/Button";
import { Input } from "@/components/ui/Input";
import { Label } from "@/components/ui/Label";
import { Separator } from "@/components/ui/Separator";

export function CodeStep({
  busy,
  onValidate,
  t,
}: {
  busy: boolean;
  onValidate: (values: CodeForm) => Promise<void>;
  t: (k: string) => string;
}) {
  const form = useForm<CodeForm>({
    resolver: zodResolver(codeSchema),
    defaultValues: { code: "" },
    mode: "onSubmit",
  });

  return (
    <Card>
      <CardHeader>
        <CardTitle>{t("codeTitle")}</CardTitle>
        <CardDescription>{t("codeDesc")}</CardDescription>
      </CardHeader>
      <CardContent>
        <form className="space-y-4" onSubmit={form.handleSubmit(onValidate)} aria-busy={busy}>
          <div className="space-y-2">
            <Label htmlFor="code">{t("codeLabel")}</Label>
            <Input
              id="code"
              placeholder={t("codePlaceholder")}
              autoComplete="one-time-code"
              inputMode="text"
              {...form.register("code")}
            />
            {form.formState.errors.code?.message && (
              <p className="text-sm text-red-600">{form.formState.errors.code.message}</p>
            )}
          </div>

          <Button type="submit" className="w-full" disabled={busy}>
            {busy ? (
              <span className="inline-flex items-center gap-2">
                <Loader2 className="h-4 w-4 animate-spin" /> {t("validating")}
              </span>
            ) : (
              t("continue")
            )}
          </Button>

          <Separator />
          <div className="text-xs text-slate-500">
            <div className="inline-flex items-start gap-2">
              <ShieldCheck className="h-4 w-4 mt-0.5" />
              <p>
                <span className="font-medium">{t("tipTitle")}:</span> {t("tipText")}
              </p>
            </div>
          </div>
        </form>
      </CardContent>
    </Card>
  );
}
