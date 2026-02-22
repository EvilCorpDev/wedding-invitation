import React from "react";
import type { ApiEvent } from "@/lib/types";
import { formatDate } from "@/lib/date";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "./ui/Card";

export function EventDetailsCard({ event, t }: { event: ApiEvent; t: (k: string) => string }) {
  return (
    <Card className="mb-4">
      <CardHeader>
        <CardTitle className="text-lg">{t("eventDetails")}</CardTitle>
        <CardDescription>
          {event.dateIso ? formatDate(event.dateIso) : ""}
          {event.venueName ? ` • ${event.venueName}` : ""}
        </CardDescription>
      </CardHeader>
      <CardContent className="space-y-3 text-sm">
        {event.venueAddress && (
          <div>
            <div className="font-medium">{t("address")}</div>
            <div className="text-slate-500 whitespace-pre-line">{event.venueAddress}</div>
          </div>
        )}
        {event.scheduleText && (
          <div>
            <div className="font-medium">{t("schedule")}</div>
            <div className="text-slate-500 whitespace-pre-line">{event.scheduleText}</div>
          </div>
        )}
        {event.dressCode && (
          <div>
            <div className="font-medium">{t("dressCode")}</div>
            <div className="text-slate-500 whitespace-pre-line">{event.dressCode}</div>
          </div>
        )}
        {event.extraInfo && (
          <div>
            <div className="font-medium">{t("extraInfo")}</div>
            <div className="text-slate-500 whitespace-pre-line">{event.extraInfo}</div>
          </div>
        )}
      </CardContent>
    </Card>
  );
}
