import { z } from "zod";

export const codeSchema = z.object({
  code: z.string().trim().min(4, "Code is too short").max(64, "Code is too long"),
});
export type CodeForm = z.infer<typeof codeSchema>;

export const guestSchema = z.object({
  firstName: z.string().trim().min(1, "Required").max(80, "Too long"),
  lastName: z.string().trim().min(1, "Required").max(80, "Too long"),
  phone: z
    .string()
    .trim()
    .min(5, "Too short")
    .max(40, "Too long")
    .refine(
      (v) => {
        const normalized = v.replace(/[\s()\-]/g, "");
        return /^\+?[0-9]{5,20}$/.test(normalized);
      },
      { message: "Enter a valid phone number" }
    ),
  email: z.string().trim().email("Enter a valid email").max(120, "Too long"),
  consentTerms: z.literal(true, { errorMap: () => ({ message: "You must accept data processing terms" }) }),
  consentMarketing: z.boolean().optional(),
});
export type GuestForm = z.infer<typeof guestSchema>;
