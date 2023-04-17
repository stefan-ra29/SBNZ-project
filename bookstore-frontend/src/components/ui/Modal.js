import { Fragment } from 'react';
import './Modal.css'

function Backdrop(props) {
    return (
        <div className='backdrop' onClick={props.onClose}></div>
    );
}

function ModalOverlay(props) {
    return (
        <div className='modal'>
            <div className='content'>{props.children}</div>
        </div>
    );
}

export default function Modal(props){
    return (
        <Fragment>
            <Backdrop onClose={props.onClose}/>
            <ModalOverlay>{props.children}</ModalOverlay>
        </Fragment>
    );
}