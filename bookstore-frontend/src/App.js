import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import BrowseBooks from "./pages/BrowseBooks";
import Header from "./components/layout/Header";
import Registration from "./pages/Registration";
import CartProvider from "./store/CartProvider";
import { useState } from "react";
import Cart from "./components/cart/Cart";
import Login from "./pages/Login";
import { ToastContainer } from "react-toastify";

function App() {
  const [cartIsShown, setCartIsShown] = useState(false);

  function showCartHandler() {
    setCartIsShown(true);
  }

  function hideCartHandler() {
    setCartIsShown(false);
  }

  return (
    <BrowserRouter>
      <ToastContainer />
      <CartProvider>
        {cartIsShown && <Cart onClose={hideCartHandler} />}

        <Header onShowCart={showCartHandler} />

        <main>
          <Routes>
            <Route index element={<Home />} />
            <Route path="browse" element={<BrowseBooks />} />
            <Route path="register" element={<Registration />} />
            <Route path="login" element={<Login />} />
          </Routes>
        </main>
      </CartProvider>
    </BrowserRouter>
  );
}

export default App;
