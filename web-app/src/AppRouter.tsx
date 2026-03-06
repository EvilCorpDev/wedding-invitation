import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";
import EmailConfirmationPage from "@/pages/EmailConfirmationPage";

export default function AppRouter() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<App />} />
                <Route path="/confirmed/:type" element={<EmailConfirmationPage />} />
            </Routes>
        </BrowserRouter>
    );
}