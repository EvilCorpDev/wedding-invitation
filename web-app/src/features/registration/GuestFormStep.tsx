import React from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { Loader2 } from "lucide-react";

import { guestSchema, type GuestForm } from "./schemas";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/Card";
import { Button } from "@/components/ui/Button";
import { Input } from "@/components/ui/Input";
import { Label } from "@/components/ui/Label";
import { Checkbox } from "@/components/ui/Checkbox";

export function GuestFormStep({
  busy,
  onSubmit,
  onReset,
  t,
}: {
  busy: boolean;
  onSubmit: (values: GuestForm) => Promise<void>;
  onReset: () => void;
  t: (k: string) => string;
}) {
  const form = useForm<GuestForm>({
    resolver: zodResolver(guestSchema),
    defaultValues: {
      firstName: "",
      lastName: "",
      phone: "",
      email: "",
      consentTerms: false,
      consentMarketing: false,
    },
    mode: "onSubmit",
  });

  return (
    <Card>
      <CardHeader>
        <CardTitle>{t("guestTitle")}</CardTitle>
        <CardDescription>{t("guestDesc")}</CardDescription>
      </CardHeader>
      <CardContent>
        <form className="space-y-4" onSubmit={form.handleSubmit(onSubmit)} aria-busy={busy}>
          <div className="grid gap-4 sm:grid-cols-2">
            <div className="space-y-2">
              <Label htmlFor="firstName">{t("firstName")}</Label>
              <Input id="firstName" autoComplete="given-name" {...form.register("firstName")} />
              {form.formState.errors.firstName?.message && (
                <p className="text-sm text-red-600">{form.formState.errors.firstName.message}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="lastName">{t("lastName")}</Label>
              <Input id="lastName" autoComplete="family-name" {...form.register("lastName")} />
              {form.formState.errors.lastName?.message && (
                <p className="text-sm text-red-600">{form.formState.errors.lastName.message}</p>
              )}
            </div>
          </div>

          <div className="grid gap-4 sm:grid-cols-2">
            <div className="space-y-2">
              <Label htmlFor="phone">{t("phone")}</Label>
              <Input id="phone" placeholder="e.g. +48 600 000 000" autoComplete="tel" inputMode="tel" {...form.register("phone")} />
              {form.formState.errors.phone?.message && (
                <p className="text-sm text-red-600">{form.formState.errors.phone.message}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="email">{t("email")}</Label>
              <Input id="email" placeholder="e.g. anna@example.com" autoComplete="email" inputMode="email" {...form.register("email")} />
              {form.formState.errors.email?.message && (
                <p className="text-sm text-red-600">{form.formState.errors.email.message}</p>
              )}
            </div>
          </div>

          <div className="space-y-3 rounded-2xl border border-slate-200 p-4">
            <div className="flex items-start gap-3">
              <Checkbox
                id="consentTerms"
                checked={form.watch("consentTerms")}
                onCheckedChange={(v) => form.setValue("consentTerms", Boolean(v), { shouldValidate: true })}
              />
              <div className="space-y-1">
                <Label htmlFor="consentTerms" className="cursor-pointer">
                  {t("consentTerms")}
                </Label>
                <p className="text-xs text-slate-500">{t("consentTermsHint")}</p>
                {form.formState.errors.consentTerms?.message && (
                  <p className="text-sm text-red-600">{form.formState.errors.consentTerms.message}</p>
                )}
              </div>
            </div>
          </div>

          <div className="flex flex-col gap-2 sm:flex-row">
            <Button type="submit" className="sm:flex-1" disabled={busy}>
              {busy ? (
                <span className="inline-flex items-center gap-2">
                  <Loader2 className="h-4 w-4 animate-spin" /> {t("submitting")}
                </span>
              ) : (
                t("submit")
              )}
            </Button>
            <Button type="button" variant="outline" className="sm:w-40" disabled={busy} onClick={onReset}>
              {t("startOver")}
            </Button>
          </div>

          <p className="text-xs text-slate-500">{t("editHint")}</p>
        </form>
      </CardContent>
    </Card>
  );
}
