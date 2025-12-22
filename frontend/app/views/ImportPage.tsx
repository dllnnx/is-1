import { useState, useRef } from 'react';
import toast from 'react-hot-toast';
import {
  type UserRole,
  useGetImportHistoryQuery,
  useImportDragonsMutation,
} from '~/gen/types.generated';

const base_url = "http://89.169.150.230:8080";

const DownloadButton = ({ operationId, role }: { operationId: number; role: UserRole }) => {
  const [isLoading, setIsLoading] = useState(false);
  
  const handleDownload = async () => {
    setIsLoading(true);
    try {
      const url = `${base_url}/dragons/import/files/${operationId}?role=${role}`;
      
      const response = await fetch(url);
      console.log("sent");
      
      if (response.ok) {
        const blob = await response.blob();
        const downloadUrl = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadUrl;
        link.setAttribute('download', `import-${operationId}.json`);
        document.body.appendChild(link);
        link.click();
        link.remove();
        window.URL.revokeObjectURL(downloadUrl);
      } else {
        toast.error('Failed to download file');
      }
    } catch (error) {
      toast.error('Failed to download file');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <button
      onClick={handleDownload}
      disabled={isLoading}
      className="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700 text-sm disabled:opacity-50"
    >
      {isLoading ? 'Downloading...' : 'Download'}
    </button>
  );
};

export const ImportPage = () => {
  const [role, setRole] = useState<UserRole>('USER');
  const [file, setFile] = useState<File | null>(null);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const { data: history, refetch: refetchHistory } = useGetImportHistoryQuery({ role });
  const [importDragons, { isLoading: isImporting }] = useImportDragonsMutation();

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = e.target.files?.[0];
    if (selectedFile) {
      setFile(selectedFile);
    }
  };

  const handleImport = async () => {
    if (!file) {
      toast.error('Please select a file');
      return;
    }

    try {
      const formData = new FormData();
      formData.append('file', file);
      
      const result = await importDragons({
        role,
        // @ts-ignore
        body: formData,
      }).unwrap();

      if (result.status === 'SUCCESS') {
        toast.success(`Imported ${result.addedCount} dragons`);
      } else {
        toast.error(result.errorMessage || 'Import failed');
      }

      setFile(null);
      if (fileInputRef.current) {
        fileInputRef.current.value = '';
      }
      refetchHistory();
    } catch (e) {
      const error = e as { data?: { message?: string; error?: string }; message?: string };
      toast.error(error.data?.message || error.data?.error || error.message || 'Failed to import dragons');
    }
  };

  return (
    <div className="p-4 w-full">
      <h1 className="text-2xl font-bold mb-4">Import Dragons</h1>

      <div className="mb-4 flex gap-2">
        <button
          onClick={() => setRole('USER')}
          className={`px-4 py-2 border ${
            role === 'USER'
              ? 'bg-blue-600 text-white border-blue-600'
              : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-100'
          }`}
        >
          USER
        </button>
        <button
          onClick={() => setRole('ADMIN')}
          className={`px-4 py-2 border ${
            role === 'ADMIN'
              ? 'bg-blue-600 text-white border-blue-600'
              : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-100'
          }`}
        >
          ADMIN
        </button>
      </div>

      <div className="mb-6 flex gap-2 items-center">
        <input
          ref={fileInputRef}
          type="file"
          accept=".json"
          onChange={handleFileChange}
          className="border border-gray-300 px-2 py-1"
        />
        <button
          onClick={handleImport}
          disabled={!file || isImporting}
          className="px-4 py-2 bg-green-600 text-white hover:bg-green-700 disabled:opacity-50 disabled:cursor-not-allowed"
        >
          {isImporting ? 'Importing...' : 'Upload'}
        </button>
      </div>

      <h2 className="text-xl font-bold mb-2">Import History</h2>
      <div className="overflow-x-auto">
        <table className="min-w-full border-collapse border border-gray-300">
          <thead className="bg-gray-100">
            <tr>
              <th className="border border-gray-300 px-4 py-2">ID</th>
              <th className="border border-gray-300 px-4 py-2">Status</th>
              <th className="border border-gray-300 px-4 py-2">Role</th>
              <th className="border border-gray-300 px-4 py-2">Added Count</th>
              <th className="border border-gray-300 px-4 py-2">Error</th>
              <th className="border border-gray-300 px-4 py-2">Created At</th>
              <th className="border border-gray-300 px-4 py-2">Actions</th>
            </tr>
          </thead>
          <tbody>
            {history && history.length > 0 ? (
              history.map((op) => (
                <tr key={op.id} className="hover:bg-gray-50">
                  <td className="border border-gray-300 px-4 py-2">{op.id}</td>
                  <td className="border border-gray-300 px-4 py-2">
                    <span
                      className={
                        op.status === 'SUCCESS' ? 'text-green-600' : 'text-red-600'
                      }
                    >
                      {op.status}
                    </span>
                  </td>
                  <td className="border border-gray-300 px-4 py-2">{op.userRole}</td>
                  <td className="border border-gray-300 px-4 py-2">
                    {op.addedCount ?? '—'}
                  </td>
                  <td className="border border-gray-300 px-4 py-2">
                    {op.errorMessage || '—'}
                  </td>
                  <td className="border border-gray-300 px-4 py-2">
                    {new Date(op.createdAt).toLocaleString()}
                  </td>
                  <td className="border border-gray-300 px-4 py-2">
                    {op.fileKey && op.status !== 'FAILED' ? (
                      <DownloadButton operationId={op.id} role={role} />
                    ) : (
                      '—'
                    )}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td
                  colSpan={6}
                  className="border border-gray-300 px-4 py-8 text-center text-gray-500"
                >
                  No import history
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};
