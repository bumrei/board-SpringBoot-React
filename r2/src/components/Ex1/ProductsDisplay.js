import React from 'react';

const ProductsDisplay = ({products, addCart}) => {

    const list = products.map(p => <li key={p.pno} onClick={() => addCart(p)}>{p.pname} --- {p.price}</li>)

    return (
        <div>
            <div className="card">
                <div className="card-header">
                    This is some text within a card body.
                </div>
                <div className="card-body">
                    This is some text within a card body.
                </div>
                <div className="card-footer">
                    This is some text within a card body.
                </div>
            </div>
            <h1>상품 목록</h1>
            <ul>
                {list}
            </ul>
        </div>
    );
};

export default ProductsDisplay;