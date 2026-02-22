export function formatDate(iso: string): string {
  try {
    const [y, m, d] = iso.split("-").map((n) => parseInt(n, 10));
    const dt = new Date(Date.UTC(y, (m || 1) - 1, d || 1));
    return dt.toLocaleDateString(undefined, {
      weekday: "long",
      year: "numeric",
      month: "long",
      day: "numeric",
    });
  } catch {
    return iso;
  }
}
