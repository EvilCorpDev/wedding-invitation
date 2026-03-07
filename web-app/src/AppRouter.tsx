import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";
import ConfirmationPage from "@/pages/ConfirmationPage";

export default function AppRouter() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<App />} />
                <Route path="/confirmed/:type" element={<ConfirmationPage />} />
            </Routes>
        </BrowserRouter>
    );
}