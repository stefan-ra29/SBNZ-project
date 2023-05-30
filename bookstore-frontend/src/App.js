import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import BrowseBooks from "./pages/BrowseBooks";
import UnauthorizedUsersRecommendedBooks from "./pages/UnauthorizedUsersRecommendedBooks";
import AuthorizedUsersRecommendedBooks from "./pages/AuthorizedUsersRecommonededBooks";
import Header from "./components/layout/Header";
import Registration from "./pages/Registration";
import CartProvider from "./store/CartProvider";
import { useState } from "react";
import Cart from "./components/cart/Cart";
import Login from "./pages/Login";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

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
            <Route path="unauthorizedUsersRecommendedBooks" element={<UnauthorizedUsersRecommendedBooks />} />
            <Route path="authorizedUsersRecommendedBooks" element={<AuthorizedUsersRecommendedBooks />} />
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
