// https://yeun.github.io/open-color/ 색상 참고 사이트

const palette = {
    cyan: [
        "#e3fafc",
        "#c5f6fa",
        "#99e9f2",
        "#66d9e8",
        "#3bc9db",
        "#22b8cf",
        "#15aabf",
        "#1098ad",
        "#0c8599",
        "#0b7285"
    ],
    green: [
        "#ebfbee",
        "#d3f9d8",
        "#b2f2bb",
        "#8ce99a",
        "#69db7c",
        "#51cf66",
        "#40c057",
        "#37b24d",
        "#2f9e44",
        "#2b8a3e"
    ],
    orange: [
        "#fff4e6",
        "#ffe8cc",
        "#ffd8a8",
        "#ffc078",
        "#ffa94d",
        "#ff922b",
        "#fd7e14",
        "#f76707",
        "#e8590c",
        "#d9480f"   
    ],
    blue: [
        "#e7f5ff",
        "#d0ebff",
        "#a5d8ff",
        "#74c0fc",
        "#4dabf7",
        "#339af0",
        "#228be6",
        "#1c7ed6",
        "#1971c2",
        "#1864ab"   
    ],
    red: [
        "#fff5f5",
        "#ffe3e3",
        "#ffc9c9",
        "#ffa8a8",
        "#ff8787",
        "#ff6b6b",
        "#fa5252",
        "#f03e3e",
        "#e03131",
        "#c92a2a"   
    ],
    main: [
        "#002A4F",
        "#1a3e2d",
        "#17607D",
        "#FFF1CE",
        "#FF9311",
        "#D64700",
        "#c92a2a",
        "#228be6",
        "#69db7c",
    ],
    postColor: [
        "red",
        "blue",
        "orange",
        "yellow",
        "green",    
    ],
    postColorHex: {
        "red": "#ff9999",
        "blue": "#9dc3e6",
        "orange": "#f4b183",
        "yellow": "#ffd966",
        "green": "#a9d18e"
    }
    
}

export const randomPalette = () => {
    const randomIndex =  Math.floor(Math.random() * 5);

    return palette.postColor[randomIndex];
}

export default palette;



