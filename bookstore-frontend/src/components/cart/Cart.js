import Modal from '../ui/Modal';
import { useContext } from 'react';
import CartContext from '../../store/cart-context';
import CartItem from './CartItem';
import { placeOrder } from '../../services/BookService';
import './Cart.css'

export default function Cart(props) {

    const cartCtx = useContext(CartContext);

    const totalAmount = `${cartCtx.totalAmount.toFixed(2)} RSD`;
    const hasItems = cartCtx.items.length > 0;

    function cartItemRemoveHandler(id) {
        cartCtx.removeItem(id);
    }

    function cartItemAddHandler(item) {
        cartCtx.addItem({...item, amount: 1});
    }

    function placeOrderHandler() {
        placeOrder(cartCtx.items);
        cartCtx.emptyCart();
        props.onClose();
    }

    const cartItems = 
        <ul className='cart-items'>
            {cartCtx.items.map(item => 
                <CartItem 
                    key={item.id} 
                    name={item.name}
                    amount={item.amount} 
                    price={item.price} 
                    author={item.author}
                    category={item.category}
                    onRemove={cartItemRemoveHandler.bind(null, item.id)} 
                    onAdd={cartItemAddHandler.bind(null, item)}>
                </CartItem>)}
        </ul>

    return (
        <Modal onClose={props.onClose}>
            {cartItems}

            <div className='total'>
                <span>Total:</span>
                <span>{totalAmount}</span>
            </div>

            <div className='actions'>
                <button className='button--alt' onClick={props.onClose}>Close</button>
                {hasItems && <button className='button' onClick={placeOrderHandler}>Order</button>}
            </div>
        </Modal>
    );
};