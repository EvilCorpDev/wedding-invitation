import { useSearchParams, Link, useParams } from "react-router-dom";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/Card";
import { useLocalStorageState } from "@/lib/storage";
import { I18N, type Lang } from "@/lib/i18n";
import { useMemo } from "react";

export default function ConfirmationPage() {
    const [lang] = useLocalStorageState<Lang>("wedding.lang", "en");
    const [params] = useSearchParams();
    const langParam = params.get("lang");
    const langFromParam = langParam ? (langParam as Lang) : lang;

    const t = useMemo(() => {
        const dict = I18N[langFromParam] || I18N.en;
        return (key: string) => dict[key] ?? key;
    }, [langFromParam]);

    const { type } = useParams();

    const status = params.get("status");

    const isPhone = type === "phone";

    let title = "";
    let message = "";

    switch (status) {
        case "ok":
            title = t(isPhone ? "confirmationPhoneOkTitle" : "confirmationEmailOkTitle");
            message = t(isPhone ? "confirmationPhoneOkMessage" : "confirmationEmailOkMessage");
            break;

        case "expired":
            title = t("confirmationExpiredTitle");
            message = t(isPhone ? "confirmationPhoneExpiredMessage" : "confirmationEmailExpiredMessage");
            break;

        case "invalid":
            title = t("confirmationInvalidTitle");
            message = t(isPhone ? "confirmationPhoneInvalidMessage" : "confirmationEmailInvalidMessage");
            break;

        case "used":
            title = t(isPhone ? "confirmationPhoneUsedTitle" : "confirmationEmailUsedTitle");
            message = t(isPhone ? "confirmationPhoneUsedMessage" : "confirmationEmailUsedMessage");
            break;

        default:
            message = t("confirmationUnknown");
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
                        {t("backToInvitation")}
                    </Link>
                </CardContent>
            </Card>
        </div>
    );
}