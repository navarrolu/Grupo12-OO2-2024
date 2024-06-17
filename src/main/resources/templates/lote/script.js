// creditos a gemini
const loteForm = document.getElementById('loteForm');
const pedidoSelect = document.getElementById('pedido');
const productoSelect = document.getElementById('producto');
const productoManualSelect = document.getElementById('productoManual');
const cantidadInput = document.getElementById('cantidad');
const cantidadManualInput = document.getElementById('cantidadManual');
const proveedorInput = document.getElementById('proveedor');
const proveedorManualInput = document.getElementById('proveedorManual');
const pedidoDetails = document.querySelector('.pedido-details');
const manualEntry = document.querySelector('.manual-entry');

// Fetch Pedidos and populate the select options
fetch('/api/pedidos')
    .then(response => response.json())
    .then(pedidos => {
        pedidos.forEach(pedido => {
            const option = document.createElement('option');
            option.value = pedido.id_pedido;
            option.textContent = pedido.descripcion;
            pedidoSelect.appendChild(option);
        });
    });

// Fetch Productos and populate the select options
fetch('/api/productos')
    .then(response => response.json())
    .then(productos => {
        productos.forEach(producto => {
            const option = document.createElement('option');
            option.value = producto.id_producto;
            option.textContent = producto.nombre;

            productoSelect.appendChild(option);
            productoManualSelect.appendChild(option.cloneNode(true));
        });
    });

pedidoSelect.addEventListener('change', () => {
    const selectedPedidoId = pedidoSelect.value;

    if (selectedPedidoId) {
        // Fetch Pedido details and populate the corresponding fields
        fetch(`/api/pedidos/${selectedPedidoId}`)
            .then(response => response.json())
            .then(pedido => {
                productoSelect.value = pedido.producto.id_producto;
                cantidadInput.value = pedido.cantidad;
                proveedorInput.value = pedido.proveedor.nombre;
                proveedorManualInput.disabled = true;
                pedidoDetails.style.display = 'block';
                manualEntry.style.display = 'none';
            })
            .catch(error => {
                console.error('Error fetching pedido details:', error);
            });
    } else {
        // Reset fields and enable manual entry
        productoSelect.value = '';
        cantidadInput.value = 1;
        proveedorInput.value = '';
        proveedorManualInput.disabled = false;
        pedidoDetails.style.display = 'none';
        manualEntry.style.display = 'block';
    }
});

loteForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const selectedPedidoId = pedidoSelect.value;
    const productoId = selectedPedidoId ? productoSelect.value : productoManualSelect.value;
    const cantidad = selectedPedidoId ? cantidadInput.value : cantidadManualInput.value;
    const proveedor = selectedPedidoId ? proveedorInput.value : proveedorManualInput.value;

    // Validate input data
    if (!productoId || !cantidad || !proveedor) {
        alert('Please fill in all required fields.');
        return;
    }

    // Create the lote data object
    const loteData = {
        pedidoId: selectedPedidoId || null,
        productoId: productoId,
        cantidad: parseInt(cantidad),
        proveedor: proveedor
    };

    // Send the lote data to the server
    fetch('/api/lotes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loteData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Lote created successfully:', data);
            // Clear the form and reset to initial state
            loteForm.reset();
            pedidoSelect.value = '';
            proveedorManualInput.disabled = false;
            pedidoDetails.style.display = 'none';
            manualEntry.style.display = 'block';
        })
        .catch(error => {
            console.error('Error creating lote:', error);
            alert('An error occurred while creating the lote.');
        });
});
