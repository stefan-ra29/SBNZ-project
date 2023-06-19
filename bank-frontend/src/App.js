import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Header from "./components/layout/Header";
import Registration from "./pages/Registration";
import Login from "./pages/Login";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function App() {

  return (
    <BrowserRouter>
      <ToastContainer />

        <Header/>

        <main>
          <Routes>
            <Route index element={<Home />} />
            <Route path="register" element={<Registration />} />
            <Route path="login" element={<Login />} />
          </Routes>
        </main>
    </BrowserRouter>
  );
}

export default App;
