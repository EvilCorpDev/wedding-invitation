export type Lang = "en" | "pl" | "ua";

export const I18N: Record<Lang, Record<string, string>> = {
  en: {
    titleFallback: "Wedding Invitation",
    subtitle: "Please enter your invitation code to continue.",
    language: "Language",

    eventDetails: "Event details",
    address: "Address",
    schedule: "Schedule",
    dressCode: "Dress code",
    extraInfo: "Extra info",

    codeTitle: "Enter your code",
    codeDesc:
      "You should have received a short code from the couple (e.g. in a message). It’s used only to identify your invitation.",
    codeLabel: "Invitation code",
    codePlaceholder: "e.g. ZAKH-7F2A",
    validating: "Validating…",
    continue: "Continue",

    tipTitle: "Tip",
    tipText:
      "Your backend should rate-limit code validation and never reveal whether a code exists by returning different error details. Keep it generic.",

    guestTitle: "Guest details",
    guestDesc: "Please provide your contact details. The couple may use them to send updates about the event.",
    firstName: "First name",
    lastName: "Last name",
    phone: "Phone",
    email: "Email",

    consentTerms: "I agree to the processing of my personal data for wedding organization purposes",
    consentTermsHint:
      "Replace this with your actual privacy clause (GDPR/RODO). Keep it short and link to full policy.",
    consentMarketing: "Optional: I agree to receive updates via SMS/email about the event",
    consentMarketingHint: "If you’ll message them updates, this consent is usually a good idea.",

    submitting: "Submitting…",
    submit: "Submit",
    startOver: "Start over",
    editHint:
      "If you made a mistake after submitting, implement an endpoint to allow edits (or let the couple handle it manually).",

    thanksTitle: "Thank you! 🎉",
    thanksDesc: "Your details have been saved. You’ll receive updates if the couple sends any.",
    nextTitle: "What’s next",
    next1: "Keep this page link — you can refresh it anytime.",
    next2: "The couple may share additional details (schedule, accommodation, transport, etc.).",
    next3: "If you need to change your contact info, message the couple.",
    refresh: "Refresh",
    anotherCode: "Use another code",

    oops: "Oops",
    genericError: "Something went wrong",
    missingToken: "Missing registration token. Please re-enter your code.",

    footer: "Built with ❤️ • Make sure your backend uses HTTPS and stores minimal data.",
  },
  pl: {
    titleFallback: "Zaproszenie ślubne",
    subtitle: "Wpisz kod zaproszenia, aby kontynuować.",
    language: "Język",

    eventDetails: "Szczegóły wydarzenia",
    address: "Adres",
    schedule: "Plan dnia",
    dressCode: "Dress code",
    extraInfo: "Dodatkowe informacje",

    codeTitle: "Wpisz kod",
    codeDesc:
      "Kod powinieneś otrzymać od pary młodej (np. w wiadomości). Służy tylko do identyfikacji zaproszenia.",
    codeLabel: "Kod zaproszenia",
    codePlaceholder: "np. ZAKH-7F2A",
    validating: "Sprawdzanie…",
    continue: "Dalej",

    tipTitle: "Wskazówka",
    tipText:
      "Backend powinien ograniczać liczbę prób (rate-limit) i nie ujawniać, czy dany kod istnieje (zwracaj ogólny błąd).",

    guestTitle: "Dane gościa",
    guestDesc:
      "Podaj proszę swoje dane kontaktowe. Para młoda może ich użyć do wysyłania aktualizacji dotyczących wydarzenia.",
    firstName: "Imię",
    lastName: "Nazwisko",
    phone: "Telefon",
    email: "E-mail",

    consentTerms: "Wyrażam zgodę na przetwarzanie moich danych osobowych w celu organizacji ślubu",
    consentTermsHint:
      "Zastąp to swoją klauzulą informacyjną (RODO). Dodaj link do pełnej polityki prywatności.",
    consentMarketing: "Opcjonalnie: zgadzam się na otrzymywanie aktualizacji SMS/e-mail dotyczących wydarzenia",
    consentMarketingHint:
      "Jeśli planujesz wysyłać aktualizacje, taka zgoda zwykle jest dobrym pomysłem.",

    submitting: "Wysyłanie…",
    submit: "Wyślij",
    startOver: "Zacznij od nowa",
    editHint:
      "Jeśli po wysłaniu zauważysz błąd, dodaj endpoint do edycji (albo poproś parę młodą o poprawkę).",

    thanksTitle: "Dziękujemy! 🎉",
    thanksDesc: "Twoje dane zostały zapisane. Otrzymasz aktualizacje, jeśli para młoda je wyśle.",
    nextTitle: "Co dalej",
    next1: "Zachowaj ten link — możesz odświeżyć stronę w każdej chwili.",
    next2: "Para młoda może udostępnić dodatkowe informacje (plan, nocleg, dojazd itp.).",
    next3: "Jeśli chcesz zmienić dane kontaktowe, napisz do pary młodej.",
    refresh: "Odśwież",
    anotherCode: "Użyj innego kodu",

    oops: "Ups",
    genericError: "Coś poszło nie tak",
    missingToken: "Brak tokenu rejestracji. Wpisz ponownie kod.",

    footer: "Zrobione z ❤️ • Upewnij się, że backend używa HTTPS i przechowuje minimalną ilość danych.",
  },
  ua: {
    titleFallback: "Запрошення на весілля",
    subtitle: "Введіть код запрошення, щоб продовжити.",
    language: "Мова",

    eventDetails: "Деталі події",
    address: "Адреса",
    schedule: "Розклад",
    dressCode: "Дрес-код",
    extraInfo: "Додаткова інформація",

    codeTitle: "Введіть код",
    codeDesc:
      "Ви мали отримати короткий код від пари (наприклад, у повідомленні). Він потрібен лише для ідентифікації запрошення.",
    codeLabel: "Код запрошення",
    codePlaceholder: "наприклад ZAKH-7F2A",
    validating: "Перевіряємо…",
    continue: "Продовжити",

    tipTitle: "Порада",
    tipText:
      "Бекенд має обмежувати кількість спроб та не розкривати, чи існує код (повертайте загальне повідомлення про помилку).",

    guestTitle: "Дані гостя",
    guestDesc: "Будь ласка, вкажіть контактні дані. Пара може використати їх для надсилання оновлень щодо події.",
    firstName: "Імʼя",
    lastName: "Прізвище",
    phone: "Телефон",
    email: "Ел. пошта",

    consentTerms: "Я погоджуюсь на обробку моїх персональних даних для організації весілля",
    consentTermsHint:
      "Замініть це на вашу реальну політику/згоду (GDPR/RODO) та додайте посилання на повний текст.",
    consentMarketing: "Необовʼязково: я погоджуюсь отримувати оновлення про подію через SMS/ел. пошту",
    consentMarketingHint:
      "Якщо ви плануєте розсилати оновлення, така згода зазвичай доречна.",

    submitting: "Надсилаємо…",
    submit: "Надіслати",
    startOver: "Почати заново",
    editHint:
      "Якщо після надсилання ви помітили помилку — додайте endpoint для редагування (або зверніться до пари).",

    thanksTitle: "Дякуємо! 🎉",
    thanksDesc: "Ваші дані збережено. Ви отримаєте оновлення, якщо пара їх надішле.",
    nextTitle: "Що далі",
    next1: "Збережіть це посилання — ви можете оновити сторінку будь-коли.",
    next2: "Пара може додати більше деталей (розклад, житло, транспорт тощо).",
    next3: "Якщо потрібно змінити контактні дані — напишіть парі.",
    refresh: "Оновити",
    anotherCode: "Інший код",

    oops: "Ой",
    genericError: "Щось пішло не так",
    missingToken: "Немає токена реєстрації. Введіть код ще раз.",

    footer: "Зроблено з ❤️ • Переконайтеся, що бекенд використовує HTTPS і зберігає мінімум даних.",
  },
};
