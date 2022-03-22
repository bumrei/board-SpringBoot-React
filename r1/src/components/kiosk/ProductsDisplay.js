import React from 'react';

const ProductsDisplay = ({products, addCart}) => {

    const list = products.map(p => <li key={p.pno} onClick={() => addCart(p)}>{p.pname} --- {p.price}</li>)

    return (
        <div>
            <h1>상품 목록</h1>
            <ul>
                {list}
            </ul>
        </div>
    );
};

export default ProductsDisplay;