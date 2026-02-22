import React from "react";
import { cn } from "./cn";

export const Input = React.forwardRef<HTMLInputElement, React.InputHTMLAttributes<HTMLInputElement>>(
    ({ className, ...props }, ref) => {
        return (
            <input
                ref={ref}
                className={cn(
                    "h-10 w-full rounded-2xl border border-slate-300 bg-white px-3 text-sm outline-none focus:ring-2 focus:ring-slate-400",
                    className
                )}
                {...props}
            />
        );
    }
);

Input.displayName = "Input";