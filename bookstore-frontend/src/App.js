import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './pages/Home';
import BrowseBooks from './pages/BrowseBooks'
import Header from './components/layout/Header';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <main>
        <Routes>
          <Route index element={<Home />} />
          <Route path='browse' element={<BrowseBooks />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
