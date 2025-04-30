import api from "../../../api/apiClient";
import { useState, useEffect, useRef } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";

function ProductEdit() {

  const { id } = useParams();
  const [productData, setProductData] = useState({
    name: "",
    description: "",
    price: "",
    stock: "",
    uploadImage: null,
    imagePath: "",
    categoryId: "",
  });

  const [categories, setCategories] = useState([]);
  const [previewImage, setPreviewImage] = useState(null);
  const [uploadedFileName, setUploadedFileName] = useState("");
  const fileInputRef = useRef(null);
  const navigate = useNavigate();

  useEffect(() => {
      const fetchData = async () => {
        try{
          const [resProduct, resCategories] = await Promise.all([
            api.get(`/admin/products/${id}`),
            api.get(`/admin/categories`)
          ]);
          setCategories(resCategories.data);
          setProductData(resProduct.data);
        } catch(error) {
          console.error('데이터 불러오기 실패', error);
        }
      };

      fetchData();
      
  }, [id])

  const modProc = async() => {
    try{
        if(window.confirm("상품을 수정하시겠습니까?")){

          console.log("ID", id);

            const param = new FormData();
            param.append("name", productData.name);
            param.append("description", productData.description);
            param.append("price", productData.price);
            param.append("stock", productData.stock);
            param.append("categoryId", productData.categoryId);

            if (productData.uploadImage) {
              param.append("uploadImage", productData.uploadImage);
            }

            const response = await api.put(
              `/admin/products/${id}`, 
               param, 
               {
                 headers: {
                  "Content-Type": "multipart/form-data",
                 }
               }
            );

            if (response.status === 200) {
                alert("수정 되었습니다.");
                navigate('/admin/product-management');
            }
        }
    } catch (error) {
        alert("수정에 실패하였습니다.");
    }
  }

  const handleChange = (e) => {
    const { name, value, files, type } = e.target;
  
    if (type === "file") {
      const file = files?.[0];
      if (file) {
        setProductData(prev => ({
          ...prev,
          [name]: file,
        }));
        setPreviewImage(URL.createObjectURL(file));
        setUploadedFileName(file.name);
      } else {
        setPreviewImage(null);
        setUploadedFileName("");
      }
    } else {
      setProductData(prev => ({
        ...prev,
        [name]: value,
      }));
    }
  };

  return (
    <div className="p-6">
      <div className="text-2xl font-bold mb-6">상품수정</div>

        <table className="min-w-full border-collapse border border-gray-200 rounded-md">
          <tbody>
            <tr className="border-b">
              <td className="px-4 py-3 bg-gray-100">상품명</td>
              <td className="px-4 py-3">
                <input
                  name="name"
                  value={productData.name}
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
                  value={productData.description}
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
                  value={productData.price}
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
                  value={productData.categoryId}
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
                  value={productData.stock}
                  onChange={handleChange}
                  className="border w-full px-2 py-1"
                  required
                />
              </td>
            </tr>
            <tr>
              <td className="px-4 py-3 bg-gray-100">이미지 업로드</td>
              <td className="px-4 py-3">
                {/* 기존 등록된 이미지 표시 */}
                {(previewImage || productData.imagePath) && (
                  <div className="mb-4">
                    <div className="flex items-center space-x-2">
                      <img
                        src={ previewImage ? previewImage : `http://localhost:8080${productData.imagePath}`}
                        alt={productData.name}
                        className="w-16 h-16 object-cover rounded"
                      />
                      <span className="text-sm text-gray-600">
                        {uploadedFileName || extractFileName(productData.imagePath)}
                      </span>
                    </div>
                  </div>
                )}
                <div>
                    {(previewImage || productData.imagePath) && (
                      <button
                        type="button"
                        onClick={() => {
                          setProductData({ ...productData, imagePath: "", uploadImage: null});
                          setPreviewImage(null);
                          setUploadedFileName("");
                          if (fileInputRef.current) {
                            fileInputRef.current.value = "";
                          }
                        }}
                        className="ml-2 bg-red-500 text-white px-4 py-2 rounded"
                      >
                        이미지 삭제
                      </button>
                    )}
                    <label htmlFor="uploadImage" className="cursor-pointer inline-block bg-gray-200 px-4 py-2 rounded">
                      { (previewImage || productData.imagePath) ? "이미지 변경" : "이미지 등록" }
                    </label>
                    <input
                      id="uploadImage"
                      name="uploadImage"
                      type="file"
                      onChange={handleChange}
                      className="hidden"
                      ref={fileInputRef}
                    />
                </div>
              </td>
            </tr>
          </tbody>
        </table>

        <div className="flex justify-end gap-2 p-4">
          <button type="button" onClick={modProc} className="bg-gray-200 px-4 py-2 rounded">수정</button>
          <Link to='/admin/product-management' className="bg-gray-200 px-4 py-2 rounded">취소</Link>
        </div>
    </div>
  );
}

function extractFileName(path) {
  const fileName = path.split('/').pop();
  return fileName.split('_').pop();
}

export default ProductEdit;
