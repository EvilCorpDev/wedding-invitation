import sticker1 from "@/assets/stickers/sticker1.jpg";
import sticker2 from "@/assets/stickers/sticker2.jpg";
import sticker3 from "@/assets/stickers/sticker3.jpg";
import sticker4 from "@/assets/stickers/sticker4.jpg";
import sticker5 from "@/assets/stickers/sticker5.jpg";
import sticker6 from "@/assets/stickers/sticker6.jpg";
import sticker7 from "@/assets/stickers/sticker7.jpg";
import sticker8 from "@/assets/stickers/sticker8.jpg";
import sticker9 from "@/assets/stickers/sticker9.jpg";
import sticker10 from "@/assets/stickers/sticker10.jpg";

const stickers = [
    { src: sticker1, style: "top-10 left-10 rotate-[-12deg]" },
    { src: sticker2, style: "top-40 right-20 rotate-[10deg]" },
    { src: sticker3, style: "top-[30%] left-[8%] rotate-[6deg]" },
    { src: sticker4, style: "top-[40%] right-[10%] rotate-[-8deg]" },
    { src: sticker5, style: "bottom-20 left-20 rotate-[15deg]" },
    { src: sticker6, style: "bottom-10 right-40 rotate-[-10deg]" },
    { src: sticker7, style: "top-[20%] right-[20%] rotate-[8deg]" },
    { src: sticker8, style: "bottom-[25%] left-[18%] rotate-[-6deg]" },
    { src: sticker9, style: "top-[70%] right-[20%] rotate-[12deg]" },
    { src: sticker10, style: "bottom-[60%] left-[20%] rotate-[10deg]" }
];

export default function StickerBackground() {
    return (
        <div className="pointer-events-none fixed inset-0 z-0 overflow-hidden">
            {stickers.map((s, i) => (
                <img
                    key={i}
                    src={s.src}
                    className={`absolute w-44 md:w-52 lg:w-60 opacity-60 shadow-xl rounded-xl ${s.style}
          hidden md:block bg-white p-2`}
                />
            ))}

            {/* mobile version (less stickers) */}
            <img
                src={sticker1}
                className="absolute top-10 left-5 w-32 opacity-55 rotate-[-10deg] md:hidden rounded-lg bg-white p-2"
            />

            <img
                src={sticker4}
                className="absolute bottom-20 right-5 w-32 opacity-55 rotate-[10deg] md:hidden rounded-lg bg-white p-2"
            />
        </div>
    );
}