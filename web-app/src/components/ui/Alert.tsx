import React from "react";
import { cn } from "./cn";

export function Alert({
  className,
  variant,
  ...props
}: React.HTMLAttributes<HTMLDivElement> & { variant?: "destructive" }) {
  const v = variant === "destructive" ? "border-red-200 bg-red-50 text-red-900" : "border-slate-200 bg-white";
  return <div className={cn("rounded-2xl border p-4", v, className)} {...props} />;
}

export function AlertTitle({ className, ...props }: React.HTMLAttributes<HTMLHeadingElement>) {
  return <div className={cn("font-semibold", className)} {...props} />;
}

export function AlertDescription({ className, ...props }: React.HTMLAttributes<HTMLParagraphElement>) {
  return <div className={cn("mt-1 text-sm", className)} {...props} />;
}
