import { useState } from "react";

export default function App() {
  const [count, setCount] = useState(0);

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>🚀 My Netlify React App</h1>
      <p>This is a basic React app deployed on Netlify.</p>

      <button style={styles.button} onClick={() => setCount(count + 1)}>
        You clicked {count} times
      </button>

      <footer style={styles.footer}>
        <p>Built with ❤️ using React + Vite</p>
      </footer>
    </div>
  );
}

const styles = {
  container: {
    textAlign: "center",
    padding: "40px",
    fontFamily: "Arial, sans-serif",
  },
  title: {
    fontSize: "2.5rem",
    marginBottom: "20px",
  },
  button: {
    padding: "10px 20px",
    background: "#0077ff",
    color: "#fff",
    border: "none",
    borderRadius: "6px",
    cursor: "pointer",
    marginTop: "10px",
  },
  footer: {
    marginTop: "40px",
    opacity: 0.5,
  }
};
