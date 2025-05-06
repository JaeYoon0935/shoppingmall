import api from "../../../api/apiClient";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

function ProductRegistration() {

  const [product, setProduct] = useState({
    id: "",
    name: "",
    description: "",
    price: "",
    stock: "",
    uploadImage: null,
    categoryId: "",
  });

  const [categories, setCategories] = useState([]);
  const [previewImage, setPreviewImage] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
      api.get("/admin/categories")
      .then(response => {
          setCategories(response.data)
      }).catch(error => {
          console.error("카테고리 불러오기 실패", error);
      })
  }, [])

  const regProc = async() => {
    try{
        if(window.confirm("상품을 등록하시겠습니까?")){

            const param = new FormData();
            param.append("name", product.name);
            param.append("description", product.description);
            param.append("price", product.price);
            param.append("stock", product.stock);
            param.append("categoryId", product.categoryId);

            if (product.uploadImage) {
              param.append("uploadImage", product.uploadImage);
            }

            const response = await api.post(
              "/admin/products", 
               param, 
               {
                 headers: {
                  "Content-Type": "multipart/form-data",
                 }
               }
            );

            if (response.status === 200) {
                alert("등록 되었습니다.");
                navigate('/admin/product-management');
            }else{
              alert("등록에 실패하였습니다.");
            }

        }
    } catch (error) {
        alert("등록에 실패하였습니다.");
    }
  }

  const handleChange = (e) => {
    const { name, value, files, type } = e.target;
  
    if (type === "file") {
      const file = files?.[0];
      if (file) {
        setProduct(prev => ({
          ...prev,
          [name]: file,
        }));
        setPreviewImage(URL.createObjectURL(file));
      } else {
        setPreviewImage(null);
      }
    } else {
      setProduct(prev => ({
        ...prev,
        [name]: value,
      }));
    }
  };

  return (
    <div className="p-6">
      <div className="text-2xl font-bold mb-6">상품등록</div>

        <table className="min-w-full border-collapse border border-gray-200 rounded-md">
          <tbody>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">상품명</td>
              <td className="px-4 py-3">
                <input
                  name="name"
                  value={product.name}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                />
              </td>
            </tr>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">상품설명</td>
              <td className="px-4 py-3">
                <textarea
                  name="description"
                  value={product.description}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                ></textarea>
              </td>
            </tr>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">가격</td>
              <td className="px-4 py-3">
                <input
                  name="price"
                  type="number"
                  value={product.price}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                />
              </td>
            </tr>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">분류명</td>
              <td className="px-4 py-3">
                <select
                  name="categoryId"
                  value={product.categoryId}
                  onChange={handleChange}
                  className="border px-2 py-1"
                  required
                >
                  <option value="">분류 선택</option>
                    {categories.map((category) => (
                        <option key={category.id} value={category.id}>
                            {category.name}
                        </option>
                    ))}
                </select>
              </td>
            </tr>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">재고</td>
              <td className="px-4 py-3">
                <input
                  name="stock"
                  type="number"
                  value={product.stock}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                />
              </td>
            </tr>
            <tr>
              <td className="px-4 py-3 bg-gray-100">이미지 업로드</td>
              <td className="px-4 py-3">
                 {previewImage && ( 
                    <div className="mb-4">
                      <div className="flex items-center space-x-2">
                        <img
                          src={previewImage}
                          alt="미리보기"
                          className="w-16 h-16 object-cover rounded"
                        />
                      </div>
                    </div>
                 )}
                <input
                  name="uploadImage"
                  type="file"
                  onChange={handleChange}
                  className="w-full"
                  required
                />
              </td>
            </tr>
          </tbody>
        </table>

        <div className="flex justify-end gap-2 p-4">
          <button type="button" onClick={regProc} className="bg-gray-200 px-4 py-2 rounded">등록</button>
          <Link to='/admin/product-management' className="bg-gray-200 px-4 py-2 rounded">취소</Link>
        </div>
    </div>
  );
}

export default ProductRegistration;
