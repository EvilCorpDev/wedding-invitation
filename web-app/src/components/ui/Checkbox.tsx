import React from "react";
import { cn } from "./cn";

type Props = {
  id?: string;
  checked?: boolean;
  onCheckedChange?: (v: boolean) => void;
  className?: string;
};

export function Checkbox({ id, checked, onCheckedChange, className }: Props) {
  return (
    <input
      id={id}
      type="checkbox"
      checked={!!checked}
      onChange={(e) => onCheckedChange?.(e.target.checked)}
      className={cn("h-4 w-4 rounded border-slate-300 text-slate-900 focus:ring-slate-400", className)}
    />
  );
}
