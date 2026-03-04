import { useSearchParams, Link } from "react-router-dom";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/Card";

export default function EmailConfirmationPage() {
    const [params] = useSearchParams();
    const status = params.get("status");

    let title = "Email confirmation";
    let message = "";

    switch (status) {
        case "ok":
            title = "Email confirmed 🎉";
            message =
                "Your email has been successfully confirmed. The couple will be able to send you updates about the wedding.";
            break;

        case "expired":
            title = "Confirmation link expired";
            message =
                "This confirmation link has expired. Please request a new confirmation email.";
            break;

        case "invalid":
            title = "Invalid confirmation link";
            message =
                "The confirmation link is invalid. Please try confirming your email again.";
            break;

        case "used":
            title = "Email already confirmed";
            message =
                "This email has already been confirmed. No further action is needed.";
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