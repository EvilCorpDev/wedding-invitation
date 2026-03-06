import { useSearchParams, Link, useParams } from "react-router-dom";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/Card";

type ConfirmationType = "email" | "phone";

export default function EmailConfirmationPage() {
    const [params] = useSearchParams();
    const { type } = useParams();

    const status = params.get("status");
    const confirmationType: ConfirmationType =
        type === "phone" ? "phone" : "email";

    const label = confirmationType === "phone" ? "phone number" : "email";

    let title = `${label} confirmation`;
    let message = "";

    switch (status) {
        case "ok":
            title =
                confirmationType === "phone"
                    ? "Phone number confirmed 🎉"
                    : "Email confirmed 🎉";
            message =
                confirmationType === "phone"
                    ? "Your phone number has been successfully confirmed. The couple will be able to send you updates about the wedding."
                    : "Your email has been successfully confirmed. The couple will be able to send you updates about the wedding.";
            break;

        case "expired":
            title = "Confirmation link expired";
            message =
                confirmationType === "phone"
                    ? "This phone confirmation link has expired. Please request a new confirmation link."
                    : "This email confirmation link has expired. Please request a new confirmation email.";
            break;

        case "invalid":
            title = "Invalid confirmation link";
            message =
                confirmationType === "phone"
                    ? "The phone confirmation link is invalid. Please try confirming your phone number again."
                    : "The confirmation link is invalid. Please try confirming your email again.";
            break;

        case "used":
            title =
                confirmationType === "phone"
                    ? "Phone number already confirmed"
                    : "Email already confirmed";
            message =
                confirmationType === "phone"
                    ? "This phone number has already been confirmed. No further action is needed."
                    : "This email has already been confirmed. No further action is needed.";
            break;

        default:
            message = "Unknown confirmation status.";
    }

    return (
        <div className="min-h-screen flex items-center justify-center bg-background px-4">
            <Card className="max-w-md w-full text-center">
                <CardHeader>
                    <CardTitle>{title}</CardTitle>
                </CardHeader>

                <CardContent className="space-y-4">
                    <p className="text-muted-foreground">{message}</p>

                    <Link
                        to="/"
                        className="inline-block bg-black text-white px-4 py-2 rounded-xl text-sm"
                    >
                        Return to invitation
                    </Link>
                </CardContent>
            </Card>
        </div>
    );
}